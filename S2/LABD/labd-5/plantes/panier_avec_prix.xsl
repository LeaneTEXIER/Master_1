<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:include href="html_wrapper.xsl"/>

<xsl:template match="/">
  <xsl:variable name='catalog_xml' select="//param[@name='catalog_xml']"/>
  <xsl:variable name='panier_xml' select="//param[@name='panier_xml']"/>
  <html>
    <xsl:call-template name="header"/>
    <body>
      <xsl:call-template name="menu"/>
      <div id="content">
          <xsl:apply-templates select="document($panier_xml)/BASKET">
            <xsl:with-param name="catalog_xml" select="$catalog_xml"/>
          </xsl:apply-templates>
      </div>
    </body>
  </html>

</xsl:template>

<xsl:template match="BASKET">
 	<xsl:param name="catalog_xml"/>
	<table>
		<th><td>COMMON</td></th>
		<th><td>Prix</td></th>
		<xsl:apply-templates select="PLANT">
			<xsl:with-param name="catalog_xml" select="$catalog_xml"/>
			<xsl:sort select="."/>
		</xsl:apply-templates>
	</table>
</xsl:template>

<xsl:template match="PLANT">
	<xsl:param name="catalog_xml"/>
	<xsl:variable name="count" select="count(preceding-sibling::PLANT)+1"/>
	<xsl:variable name="plant_common" select="."/>
 	<tr>
 		<td><xsl:value-of select="."/></td>
 		<td><xsl:value-of select="document($catalog_xml)//PLANT[./COMMON = $plant_common]/PRICE"/></td>
 		<td><a href="panier_supprimer.php?no_item={$count}" >Retirer</a></td>
 	</tr>
 	
</xsl:template>
</xsl:stylesheet>
