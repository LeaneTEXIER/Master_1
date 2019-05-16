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
          <xsl:apply-templates select="document($panier_xml)/BASKET"/>
      </div>
    </body>
  </html>

</xsl:template>

<xsl:template match="BASKET">
	<table>
		<th><td>COMMON</td></th>
		<th><td>Qte</td></th>
		<xsl:apply-templates select="PLANT">	
			<xsl:sort select="."/>
		</xsl:apply-templates>
	</table>
</xsl:template>

<xsl:template match="PLANT">
	<xsl:variable name="count" select="count(preceding-sibling::PLANT)+1"/>
	<xsl:variable name="common" select="."/>
	<xsl:variable name="nb" select="count(//PLANT[text() = $common])"/>
	<xsl:variable name="alreadyInTab" select="count(preceding-sibling::PLANT[text() = $common])"/>
	<xsl:if test="$alreadyInTab=0">
		<tr>
	 		<td><xsl:value-of select="."/></td>
	 		<td><xsl:value-of select="$nb"/></td>
 		</tr>
	</xsl:if>
</xsl:template>

</xsl:stylesheet>

