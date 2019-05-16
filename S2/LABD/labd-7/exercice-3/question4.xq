let $plantOrder := "plant_order.xml"
let $plantCatalog := "plant_catalog.xml"

return 
<PRICE>

    {
        let $somme := sum(
            for $plant in doc($plantOrder)//PLANT

                let $common := $plant/COMMON/text()
                let $price := doc($plantCatalog)//PLANT[COMMON/text()=$common]/PRICE/text()
                let $quantity := $plant/QUANTITY/text()
                return number(substring($price, 2)) * number($quantity)
        )    
        return round-half-to-even($somme, 2)
    }
</PRICE>