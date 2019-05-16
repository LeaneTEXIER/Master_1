:IP adddup $8820 ;
slave
start

master
: main
  $A $B adddup rot - *
7seg
;
start
  main
endprogram
