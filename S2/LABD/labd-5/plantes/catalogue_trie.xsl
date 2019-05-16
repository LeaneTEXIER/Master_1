<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:include href="html_wrapper.xsl"/>

<xsl:template match="/">
  <xsl:variable name='catalog_xml' select="//param[@name='catalog_xml']"/>

  <html>
    <xsl:call-template name="header"/>
    <body>
      <xsl:call-template name="menu"/>
      <div id="content">
          <xsl:apply-templates select="document($catalog_xml)//CATALOG">
            <xsl:with-param name="cle_tri" select="//param[@name='cle_tri']"/>
          </xsl:apply-templates>
      </div>
    </body>
  </html>

</xsl:template>

<xsl:template match="CATALOG">
  <xsl:param name="cle_tri"/>
 	<table>
		<!-- En tete -->
		<xsl:apply-templates select="//PLANT[1]" mode="head"/>
		<!-- Infos de chaque plante -->
		<xsl:apply-templates select="//PLANT" mode="body">
			<xsl:sort select="*[local-name()=$cle_tri]"/>
		</xsl:apply-templates>
	</table>
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
		<a href=" ?cle_tri={local-name()}">
			<xsl:value-of select="local-name()" />
		</a>
	</th>
</xsl:template>


<xsl:template match="*" mode="valeur">
	<td>
		<xsl:value-of select="."/>
	</td>
</xsl:template>

</xsl:stylesheet>
