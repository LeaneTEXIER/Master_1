<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


<xsl:param name="no_item"/>
<xsl:template match="/">
	<xsl:apply-templates select="BASKET">
           	<xsl:with-param name="plant"/>
           </xsl:apply-templates>
</xsl:template>

<xsl:template match="BASKET">
	<BASKET>
		<xsl:apply-templates select="PLANT"/>
	</BASKET>
</xsl:template>

<xsl:template match="PLANT">
	<xsl:if test="position() != $no_item">
		<xsl:copy-of select="."/>
	</xsl:if>
</xsl:template>



</xsl:stylesheet>

