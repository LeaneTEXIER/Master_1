#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "../include/ifile.h"
#include <assert.h>
#include "../include/vol.h"
#include "../include/mbr.h"
#include "../include/tools.h"

#define READ_EOF (-1)

/* the file bloc number of a given character position in a file */
#define bloc_of_pos(pos) ((pos) / SECTOR_SIZE)
/* the index in a bloc of given character position in a file */
#define ibloc_of_pos(pos) ((pos) % SECTOR_SIZE)

void read_inode(unsigned int inumber, struct inode_s *inode){
  read_bloc_n(current_volume, inumber, (unsigned char *)inode, sizeof(struct inode_s));
}

void write_inode(unsigned int inumber, struct inode_s *inode){
  write_bloc_n(current_volume, inumber, (unsigned char *)inode, sizeof(struct inode_s));
}

unsigned int create_inode(enum file_type_e type){
  struct inode_s inode;
  unsigned int inumber = new_bloc();
  if(inumber != BLOC_NULL){
    inode.file_size = 0;
    inode.file_type = type;
    for(int i = 0; i<NB_DIRECT; i++){
      inode.direct[i] = BLOC_NULL;
    }
    inode.indirect = BLOC_NULL;
    inode.indirect2 = BLOC_NULL;
    inode.magic = INODE_MAGIC;
  }
  write_inode(inumber, &inode);
  return inumber;
}

void delete_indirect(int indirect_nb){
  int INDIRECT[NB_INDIRECT];
  read_bloc_n(current_volume, indirect_nb, (unsigned char *)INDIRECT, NB_INDIRECT*sizeof(int));
  for(int i = 0; i<NB_INDIRECT; i++){
    if(INDIRECT[i] != BLOC_NULL){
      free_bloc(INDIRECT[i]);
    }
  }
  free_bloc(indirect_nb);
}

int delete_inode(unsigned int inumber){
  struct inode_s inode;
  read_inode(inumber, &inode);
  assert(inode.magic == INODE_MAGIC);
  for(int i = 0; i<NB_DIRECT; i++){
    if(inode.direct[i] != BLOC_NULL){
      free_bloc(inode.direct[i]);
    }
  }
  if(inode.indirect != BLOC_NULL){
    delete_indirect(inode.indirect);
  }
  if(inode.indirect2 != BLOC_NULL){
    int INDIRECT[NB_INDIRECT];
    read_bloc_n(current_volume, inode.indirect2, (unsigned char *)INDIRECT, NB_INDIRECT*sizeof(int));
    for(int i = 0; i<NB_INDIRECT; i++){
      if(INDIRECT[i] != BLOC_NULL){
        delete_indirect(INDIRECT[i]);
      }
    }
  }
  free_bloc(inumber);
  return RETURN_SUCCESS;
}

int vb_of_fb_indirect(unsigned int inumber, unsigned int fbloc, unsigned int do_alloc){
  int res;
  int INDIRECT[NB_INDIRECT];
  read_bloc_n(current_volume, inumber, (unsigned char *)INDIRECT, NB_INDIRECT*sizeof(int));
  res = INDIRECT[fbloc];
  if(do_alloc && res==BLOC_NULL){
    res = new_bloc();
    if(res!=BLOC_NULL){
      INDIRECT[fbloc]=res;
      write_bloc_n(current_volume, inumber, (unsigned char *)INDIRECT, NB_INDIRECT*sizeof(int));
    }
  }
  return res;
}

unsigned int vbloc_of_fbloc(unsigned int inumber, unsigned int fbloc, unsigned int do_alloc){
  struct inode_s inode;
  int res;
  read_inode(inumber, &inode);
  assert(inode.magic == INODE_MAGIC);
  if(fbloc<NB_DIRECT){
    res = inode.direct[fbloc];
    if(do_alloc && res==BLOC_NULL){
      res = new_bloc();
      if(res!=BLOC_NULL){
        inode.direct[fbloc]=res;
        write_inode(inumber, &inode);
      }
    }
    return res;
  }
  fbloc -= NB_DIRECT;
  // Indirect
  if(fbloc<NB_INDIRECT){
    if(inode.indirect == BLOC_NULL){
      if(do_alloc){
        res = new_bloc();
        if(res!=BLOC_NULL){
          inode.indirect = res;
          write_inode(inumber, &inode);
          int INDIRECT[NB_INDIRECT];
          for(int i=0; i<NB_INDIRECT; i++){
            INDIRECT[i] = BLOC_NULL;
          }
          res = new_bloc();
          if(res!=BLOC_NULL){
            INDIRECT[fbloc]=res;
            write_bloc_n(current_volume, inode.indirect, (unsigned char *)INDIRECT, NB_INDIRECT*sizeof(int));
          }
        }
        return res;
      }
      return BLOC_NULL;
    }
    return vb_of_fb_indirect(inode.indirect, fbloc, do_alloc);
  }
  fbloc -= NB_INDIRECT;
  if(fbloc>=NB_INDIRECT*NB_INDIRECT){
    printf("fbloc trop grand\n");
    return BLOC_NULL;
  }
  // Indirect2
  int fbloc_2_1 = fbloc / NB_INDIRECT;
  int fbloc_2_2 = fbloc % NB_INDIRECT;
  if(inode.indirect2 == BLOC_NULL){
    if(do_alloc){
      res = new_bloc();
      if(res!=BLOC_NULL){
        inode.indirect2 = res;
        write_inode(inumber, &inode);
        int INDIRECT[NB_INDIRECT];
        for(int i=0; i<NB_INDIRECT; i++){
          INDIRECT[i] = BLOC_NULL;
        }
        res = new_bloc();
        if(res!=BLOC_NULL){
          int indirect2_2 = res;
          INDIRECT[fbloc_2_1]=res;
          write_bloc_n(current_volume, inode.indirect2, (unsigned char *)INDIRECT, NB_INDIRECT*sizeof(int));
          for(int i=0; i<NB_INDIRECT; i++){
            INDIRECT[i] = BLOC_NULL;
          }
          res = new_bloc();
          if(res!=BLOC_NULL){
            INDIRECT[fbloc_2_2]=res;
            write_bloc_n(current_volume, indirect2_2, (unsigned char *)INDIRECT, NB_INDIRECT*sizeof(int));
          }
        }
      }
      return res;
    }
    return BLOC_NULL;
  }
  res = vb_of_fb_indirect(inode.indirect2, fbloc_2_1, do_alloc);
  if(res!=BLOC_NULL){
    return vb_of_fb_indirect(res, fbloc_2_2, do_alloc);
  }
  return BLOC_NULL;
}

unsigned int create_ifile(enum file_type_e type){
    unsigned int inumber;
    inumber = create_inode(type);
    ffatal(inumber, "unable to create inode");
    return inumber;
}

int delete_ifile(unsigned int inumber){
    int status;
    status = delete_inode(inumber);
    ffatal(status == RETURN_SUCCESS, "unable to delete inode");
    return RETURN_SUCCESS;
}

/*------------------------------
  Open, close and flush file
  ------------------------------------------------------------*/

int open_ifile(struct file_desc_t *fd, unsigned int inumber){
    unsigned int first_bloc;
    struct inode_s inode;
    /* we are opening the designed file! */
    fd->inumber = inumber;
    read_inode (inumber, &inode);
    /* other trivial init */
    fd->file_size = inode.file_size;
    fd->offset = 0;
    /* the buffer is full of zeros if the first bloc is zero, loaded
       with this first bloc otherwise */
    first_bloc = vbloc_of_fbloc(inumber, 0, FALSE);
    if (! first_bloc){
      memset(fd->buf, 0, SECTOR_SIZE);
    }
    else{
      read_bloc(current_volume, first_bloc, (unsigned char *)fd->buf);
    }
    /* last trivial */
    fd->dirty = FALSE;
    return RETURN_SUCCESS;
}

void close_ifile(struct file_desc_t *fd){
    struct inode_s inode;
    /* if the buffer is dirty, flush the file */
    flush_ifile(fd);
    /* update the inode information (size) */
    read_inode(fd->inumber, &inode);
    inode.file_size = fd->file_size;
    write_inode(fd->inumber, &inode);
}

/* note that flush don't need to worry about the bloc allocation; a
   previous write operation has already done it. */
void flush_ifile(struct file_desc_t *fd){
    unsigned int fbloc; /* bloc index in the file */
    unsigned int vbloc; /* bloc index in the volume */
    if (fd->dirty) {
	     /* compute the number of the bloc on the volume associated to
	      the buffer */
	     fbloc = bloc_of_pos(fd->offset);
       vbloc = vbloc_of_fbloc(fd-> inumber, fbloc, TRUE);
       /* write back the buffer */
       write_bloc(current_volume, vbloc, (unsigned char *)fd->buf);
       /* done */
       fd->dirty = FALSE ;
    }
}

/*------------------------------
  Seek in a file
  ------------------------------------------------------------*/

/* move the cursor of offset positions. */
void seek_ifile(struct file_desc_t *fd, int offset){
    unsigned int old_pos = fd->offset;
    unsigned int fbloc, vbloc;
    /* update the position */
    fd->offset += offset;
    /* does the seek imply a jump in another bloc? */
    if (bloc_of_pos(fd->offset) != bloc_of_pos(old_pos)) {
	     /* flush */
	     flush_ifile(fd);
	     /* the bloc index of the new buffer */
	     fbloc = bloc_of_pos(fd->offset);
	     vbloc = vbloc_of_fbloc(fd->inumber, fbloc, FALSE);
       if (! vbloc){
	     /* the bloc #0 is full of zeros */
          memset(fd->buf, 0, SECTOR_SIZE);
       }
       else{
	     /* load the bloc */
          read_bloc(current_volume, vbloc, (unsigned char *)fd->buf);
      }
    }
}

/* move the cursor at offset */
void seek2_ifile(struct file_desc_t *fd, int offset){
    seek_ifile(fd, offset - fd->offset);
}

/*------------------------------
  Read a char in a file
  ------------------------------------------------------------*/
int readc_ifile(struct file_desc_t *fd){
    char c;
    /* eof? */
    if (fd->offset > fd->file_size){
	     return READ_EOF;
    }
    /* the data is in the buffer, just return it */
    c = fd->buf[ibloc_of_pos(fd->offset)];
    /* seek + 1 */
    seek_ifile(fd, 1);
    return c;
}

/*------------------------------
  Write a char in a file
  ------------------------------------------------------------*/

/* return the  pos in the file ; RETURN_FAILURE in case of error */
int writec_ifile(struct file_desc_t *fd, char c) {
    unsigned int ibloc;
    /* write the char in the buffer */
    fd->buf[ibloc_of_pos(fd->offset)] = c;
    /* first write in the bloc ? ensure the data bloc allocation */
    if (! fd->dirty) {
        ibloc = vbloc_of_fbloc(fd->inumber, bloc_of_pos(fd->offset), TRUE);
        if (! ibloc){
            return RETURN_FAILURE;
        }
        fd->dirty = TRUE;
    }
    /* is the buffer full? */
    if (ibloc_of_pos(fd->offset) == SECTOR_SIZE-1) {
	     /* write the buffer */
       ibloc = vbloc_of_fbloc(fd->inumber, bloc_of_pos(fd->offset), FALSE);
	     write_bloc(current_volume, ibloc, (unsigned char *)fd->buf);
	     /* read the new buffer */
	     ibloc = vbloc_of_fbloc(fd->inumber, bloc_of_pos(fd->offset+1), FALSE);
	     if (! ibloc){
	        memset(fd->buf, 0, SECTOR_SIZE);
      }
	    else{
	       read_bloc(current_volume, ibloc, (unsigned char *)fd->buf);
      }
	    fd->dirty = FALSE;
    }
    /* update the file cursor and size */
    if (fd->file_size < fd->offset){
	     fd->file_size = fd->offset;
    }
    fd->offset++;
    /* the position of the written char */
    return fd->offset - 1;
}

/*------------------------------
  Read from file
  ------------------------------------------------------------*/
int read_ifile(struct file_desc_t *fd, void *buf, unsigned int nbyte){
    unsigned int i;
    int c;
    /* eof? */
    if (fd->offset >= fd->file_size){
      return READ_EOF;
    }
    /* read one by one */
    for (i = 0; i < nbyte; i++) {
	     if ((c = readc_ifile(fd)) == READ_EOF) {
	        return i;
	     }
	     *((char *)buf+i) = c;
    }
    return i;
}

/*------------------------------
  Write to file
  ------------------------------------------------------------*/
int write_ifile(struct file_desc_t *fd, const void *buf, unsigned int nbyte){
    int i;
    /* write one by one */
    for (i = 0; i < nbyte; i++){
      if (writec_ifile(fd, *((char *)buf+i)) == RETURN_FAILURE){
	       return RETURN_FAILURE;
      }
    }
    return nbyte;
}
