<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:include href="html_wrapper.xsl"/>

<xsl:template match="/">
  <xsl:variable name='catalog_xml' select="//param[@name='catalog_xml']"/>
  <xsl:variable name='families_xml' select="//param[@name='families_xml']"/>
  <xsl:variable name='family' select="//param[@name='family']"/>
  <xsl:variable name="sort_key" select="//param[@name='sort_key']"/>
  <!-- attention, il ne sait pas gerer la transmission des arbres XML -->

  <html>
    <xsl:call-template name="header"/>
    <body>
      <xsl:call-template name="menu"/>
      <div id="content">
          <xsl:apply-templates select="document($families_xml)/CLASSIFICATION"
            mode="generate-select">
              <xsl:with-param name='default' select="$family"/>
          </xsl:apply-templates>

          <xsl:apply-templates select="document($catalog_xml)//CATALOG">
            <xsl:with-param name="sort_key" select="$sort_key"/>
            <xsl:with-param name="family" select="$family"/>
            <xsl:with-param name="families_xml" select="$families_xml"/>
          </xsl:apply-templates>
      </div>
    </body>
  </html>

</xsl:template>

<xsl:template match="CLASSIFICATION" mode="generate-select">
  <xsl:param name="default"/>
  <form>
    <b>Filtrer par Famille</b> : <select name="family" onChange="javascript:submit()">
      <option value="">Aucune</option>
      <xsl:apply-templates select="FAMILY" mode="generate-select">
        <xsl:with-param name="default" select="$default"/>
      </xsl:apply-templates>
    </select>
  </form>
</xsl:template>

<xsl:template match="FAMILY" mode="generate-select">
	<xsl:param name="default"/>
	<xsl:variable name="family_name" select="./NAME/text()"/>
	<xsl:choose>
		<xsl:when test="$default = $family_name">
			<option value ="{$family_name}" selected="selected"><xsl:value-of select="$family_name"/></option>
		</xsl:when>
		<xsl:otherwise>
			<option value ="{$family_name}"><xsl:value-of select="$family_name"/></option>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<xsl:template match="CATALOG">
	<xsl:param name="sort_key"/>
	<xsl:param name="family"/>
	<xsl:param name="families_xml"/>
	<xsl:variable name="catalog" select="."/>
	<table>
		<!-- En tete -->
		<xsl:apply-templates select="//PLANT[1]" mode="head"/>
		<!-- Infos de chaque plante -->
		<xsl:choose>
			<xsl:when test="$family = ''">
				<xsl:apply-templates select="//PLANT" mode="body">
					<xsl:sort select="*[local-name()=$sort_key]"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="document($families_xml)//FAMILY[NAME = $family]/SPECIES">
					<xsl:variable name="espece" select="."/>
					<xsl:apply-templates select="$catalog//PLANT[BOTANICAL/text() = $espece]" mode="body">
						<xsl:sort select="*[local-name()=$sort_key]"/>
					</xsl:apply-templates>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
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
  	<xsl:param name="family"/>
	<th>
		<a href=" ?sort_key={local-name()}&amp;family={$family}">
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
