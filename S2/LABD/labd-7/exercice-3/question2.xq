let $plantFamilies := "plant_families.xml"
let $plantCatalog := "plant_catalog.xml"

return 
<CATALOG>
{
    for $light in distinct-values(doc($plantCatalog)//LIGHT) 
      return <LIGHT><EXPOSURE>{$light}</EXPOSURE>

        {
            for $plant in doc($plantCatalog)//PLANT[LIGHT/text()=$light] 
                return <PLANT>
                {
                    for $attribut in $plant/child::element() 
                        where name($attribut) != "LIGHT"
                            return $attribut
                } 
       </PLANT>
       }
      </LIGHT>
}
</CATALOG>