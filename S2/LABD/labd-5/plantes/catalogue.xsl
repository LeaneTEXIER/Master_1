<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" encoding="UTF-8"/>

  <xsl:template match="/">
  	<html>
  		<head>
		        <title>Catalogue de plantes</title>
		        <style>
			        table,tr,td, th {
			          border-width:2px 2px 2px 2px;
			          border-style:solid;
			          border-collapse:collapse;
			          font-size: 14px;
			        }
		        </style>
      		</head>
      		<body>
        			<table>
		            	<!-- En tete -->
		            	<xsl:apply-templates select="//PLANT[1]" mode="head"/>
		            	<!-- Infos de chaque plante -->
		            	<xsl:apply-templates select="//PLANT" mode="body"/>
		        </table>
      		</body>
    	</html>
  </xsl:template>
  
  
  <xsl:template match="PLANT" mode="head">
    	<tr>
              	<xsl:apply-templates select="*" mode="entete"/>
            </tr>
  </xsl:template>
  
    <xsl:template match="PLANT" mode="body">
    	<xsl:variable name="COMMON" select="COMMON/text()"/>
    	<tr>
              	<xsl:apply-templates select="*" mode="valeur"/>
              	<td><a href="panier_ajouter.php?plant={$COMMON}">Add</a></td>
            </tr>
  </xsl:template>


<xsl:template match="*" mode="entete">
	<th>
		<xsl:value-of select="local-name()" />
	</th>
</xsl:template>


<xsl:template match="*" mode="valeur">
	<td>
		<xsl:value-of select="."/>
	</td>
</xsl:template>


</xsl:stylesheet>
