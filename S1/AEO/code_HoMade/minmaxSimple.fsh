:IP minmax $8810;

slave
start

master
:lire_switch
  $1f
  btn
  switch
;
: main
  lire_switch
  lire_switch
  lire_switch
  $1f
	btn
  minmax
  7seg
  $1f
	btn
  7seg
  $1f
	btn
;

start
  main
endprogram
