:IP rdm $9010;

:IP square2 $C814;

slave
start

master
: main
  ticraz
  0
  $10001
  begin
    swap
    rdm
    square2
    $ffe001
    <=
    if
      inc
    endif
    swap
    dec
    dup
    0
    <=
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
