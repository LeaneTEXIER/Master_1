xquery version "1.0";

import module namespace exp = "http://fil.univ-lille1.fr/exp" at "expression.xq";

exp:eval-var("expression.xml", "variables.xml")