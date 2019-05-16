slave 
PC pf := get
PC s2 := { xnum add put }
start

master 
: main	
    $10 
    M2S
    b>>x
    pf wait
    s2 wait
    S2M
    >>x
    7seg 
    $1F 
    btn
    S2M
    7seg 
    $1F 
    btn
;

start
    main
endprogram
