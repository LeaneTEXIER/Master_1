let $maisons := "maisons.xml"
return

<html>
	<head>
        <title>Surfaces maisons</title>
        <style>
        table,tr,td, th &#123;
          border-width:2px 2px 2px 2px;
          border-style:solid;
        &#125;
        </style>
    </head>

<body>
<table>
<thead>
<tr><th>Maisons</th><th>Surfaces (m2)</th></tr> 
</thead>
 {
    for $maison in doc($maisons)//maison
        let $somme := sum(
            for $etage in $maison/child::element(), $piece in $etage/child::element()
                return $piece/@surface-m2)
    return
        <tr>
            <td>Maison {string($maison/@id)}</td>
            <td>{$somme}</td>
        </tr>
 }
</table>
</body>
</html>