let $plantFamilies := "plant_families.xml"
let $plantCatalog := "plant_catalog.xml"

return 
<CATALOG>
{
    for $plant in doc($plantCatalog)//PLANT 
       return <PLANT>
       {
           for $attribut in $plant/child::element() 
                return $attribut
       } 
       
       {
           let $botanical := $plant/BOTANICAL/text()
           let $family := doc($plantFamilies)//FAMILY[SPECIES/text()=$botanical]/NAME/text()
           return <FAMILY>{$family}</FAMILY>
       }
       </PLANT>
}
</CATALOG>