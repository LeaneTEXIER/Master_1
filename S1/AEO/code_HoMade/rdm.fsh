:IP rdm $8810;
:IP rdm2 $8010;

slave
start

master
: main
  $30
  for
    rdm2
  next
  rdm
  7seg
;

start
  main
  $1f
  btn
endprogram
