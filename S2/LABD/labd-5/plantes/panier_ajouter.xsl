<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:param name="plant"/>
<xsl:template match="/">
	<xsl:apply-templates select="BASKET">
           	<xsl:with-param name="plant"/>
           </xsl:apply-templates>
</xsl:template>

<xsl:template match="BASKET">
	<BASKET>
		<xsl:apply-templates select="PLANT"/>
		<PLANT><xsl:value-of select="$plant"/></PLANT>
	</BASKET>
</xsl:template>

<xsl:template match="PLANT">
	<xsl:copy-of select="."/>
</xsl:template>

</xsl:stylesheet>
