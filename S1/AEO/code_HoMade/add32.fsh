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
  +
  7segdup
  $10
  ->
  led
;

start
  main
  $1f
  btn
endprogram
