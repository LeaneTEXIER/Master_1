xquery version "1.0";
module namespace exp = "http://fil.univ-lille1.fr/exp";

declare default element namespace "http://www.expression.org";
declare option saxon:output "omit-xml-declaration=yes";

(: Question 1 :)

declare function exp:printrec($node as node()) as xs:string{
    switch(name($node))
		case("var") return $node/text()
		case("cons") return $node/text()
        case("op") return concat("(", exp:printrec($node/*[1]), $node/@val, exp:printrec($node/*[2]), ")")
		default return error(xs:QName("ERROR"), "Error, expression incorrect")
};

declare function exp:print($name as xs:string) as xs:string{
	exp:printrec(doc($name)/expr/*)
};

(: Question 2 :)

declare function exp:evalrec($node as node()) as xs:integer{
    switch(name($node))
		case("cons") return xs:integer($node/text())
        case("op") return
            let $gauche := exp:evalrec($node/*[1])
            let $droite := exp:evalrec($node/*[2])
            return
                switch($node/@val)
                    case ("+") return $gauche + $droite
                    case ("-") return $gauche - $droite
                    case ("*") return $gauche * $droite
                    case ("/") return $gauche idiv $droite
                    default return error(xs:QName("ERROR"), "Error, operator incorrect")
		case("var") return error(xs:QName("ERROR"), "Error, expression incorrect. Variable not allowed")
		default return error(xs:QName("ERROR"), "Error, expression incorrect")
};

declare function exp:eval($name as xs:string) as xs:integer{
    exp:evalrec(doc($name)/expr/*)
};

(: Question 4 :) 

declare function exp:get-val-var($variables, $node) as xs:integer{
        if(count(doc($variables)//*[./name()=$node])>1) then
            error(xs:QName("ERROR"), "Error, multiple definition of the variable")
        else if(count(doc($variables)//*[./name()=$node])=0) then
            error(xs:QName("ERROR"), "Error, variable undefined")
		else
            data(doc($variables)//*[./name()=$node]/text())
};

declare function exp:eval-varrec($node as node(), $variables) as xs:integer{
    switch(name($node))
		case("cons") return xs:integer($node/text())
        case("op") return
            let $gauche := exp:eval-varrec($node/*[1], $variables)
            let $droite := exp:eval-varrec($node/*[2], $variables)
            return
                switch($node/@val)
                    case ("+") return $gauche + $droite
                    case ("-") return $gauche - $droite
                    case ("*") return $gauche * $droite
                    case ("/") return $gauche idiv $droite
                    default return error(xs:QName("ERROR"), "Error, operator incorrect")
		case("var") return exp:get-val-var($variables, $node)
		default return error(xs:QName("ERROR"), "Error, expression incorrect")
};

declare function exp:eval-var($name as xs:string, $variables as xs:string) as xs:integer{
    exp:eval-varrec(doc($name)/expr/*, $variables)
};

(: Question 4 :)

declare function exp:simplifierec($node as node(), $variables) as element() {
	switch (name($node))
        case("cons") return <cons>{$node/text()}</cons>
        case("op") return exp:simplifierecOperation($node/*[1], $node/*[2], $node/@val, $variables)
        case("var") return
            if (count(doc($variables)//*[./name()=$node])>1) then
                error(xs:QName("ERROR"), "Error, multiple definition of the variable")
            else if (count(doc($variables)//*[./name()=$node])=0) then
                <var>{$node/text()}</var>
            else 
                <cons>{doc($variables)//*[./name()=$node]/text()}</cons>
		default return error(xs:QName("ERROR"), "Error, node incorrect")
};

declare function exp:simplifierecOperation($expr1 as node(), $expr2 as node(), $op as xs:string, $variables) {
	let $gauche := exp:simplifierec($expr1, $variables)
	let $droite := exp:simplifierec($expr2, $variables)
	return
        if ($gauche/name() = "cons" and $droite/name() = "cons") then
            switch ($op)
                case("+") return <cons>{$gauche/text() + $droite/text()}</cons>
                case("-") return <cons>{$gauche/text() - $droite/text()}</cons>
                case("*") return <cons>{$gauche/text() * $droite/text()}</cons>
                case("/") return <cons>{$gauche/text() idiv $droite/text()}</cons>
                default return error(xs:QName("ERROR"), "Error, operator incorrect")
        else
            <op val="{$op}">{$gauche} {$droite}</op>
};

declare function exp:simplifie($name as xs:string , $variables as xs:string) as element(){
	<expr xsi:schemaLocation="http://www.expression.org expression.xsd"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns="http://www.expression.org">
        {
            exp:simplifierec(doc($name)/expr/*, $variables)
        }
	</expr>
};

