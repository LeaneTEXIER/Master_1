:IP minmax $f010;

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
  begin
    lire_switch
    minmax
    7segdup
    $2ffffff
    delay
    swap
    7segdup
  again
;

start
  main
endprogram
