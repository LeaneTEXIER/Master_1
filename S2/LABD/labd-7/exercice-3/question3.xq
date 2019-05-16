let $plantFamilies := "plant_families.xml"
let $plantCatalog := "plant_catalog.xml"

return 
<CATALOG>
{
    for $light in distinct-values(doc($plantCatalog)//LIGHT) 
        order by $light
      return <LIGHT><EXPOSURE>{$light}</EXPOSURE>

        {
            for $plant in doc($plantCatalog)//PLANT[LIGHT/text()=$light] 
                order by $plant/COMMON/text()
                return <PLANT>
            {
                for $attribut in $plant/child::element() 
                    where name($attribut) != "LIGHT"
                        return $attribut
            } 
            {
                let $botanical := $plant/BOTANICAL/text()
                let $family := doc($plantFamilies)//FAMILY[SPECIES/text()=$botanical]/NAME/text()
                return <FAMILY>{$family}</FAMILY>
            }              
       </PLANT>
       }
      </LIGHT>
}
</CATALOG>