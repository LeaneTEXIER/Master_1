:IP rdm $9010;
:IP square2 $C814;
:IP cmpFFE001 $AB00;
:IP detectefin $AB01;
:IP generateFFFF $8B02;

slave
start

master
: main
  ticraz
  0
  generateFFFF
  begin
    swap
    rdm
    square2
    cmpFFE001
    add
    swap
    dec
    dup
    detectefin
  until
  pop
  tic
  swap
  7seg
  $1f
  btn
  dup
  16
  ->
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
