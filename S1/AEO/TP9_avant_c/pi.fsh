:IP rdm $8810;

slave
start

master
: main
  ticraz
  0
  $10000
  for
    rdm
    dup
    $fff
    and
    dup
    mul16
    swap
    12
    ->
    $fff
    and
    dup
    mul16
    add
    $ffe001
    <=
    if
      inc
    endif
  next
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
