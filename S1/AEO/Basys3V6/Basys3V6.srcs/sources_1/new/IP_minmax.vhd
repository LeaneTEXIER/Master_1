----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/19/2018 08:13:51 AM
-- Design Name: 
-- Module Name: IP_minmax - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity IP_minmax is
    GENERIC (Mycode : std_logic_vector(10 downto 0):="00000010000" );
    Port ( N2in : in STD_LOGIC_VECTOR(31 downto 0);
           Nin : in STD_LOGIC_VECTOR(31 downto 0);
           Tin : in STD_LOGIC_VECTOR(31 downto 0);
           Nout : out STD_LOGIC_VECTOR(31 downto 0);
           Tout : out STD_LOGIC_VECTOR(31 downto 0);
           IPcode : in  STD_LOGIC_vector(10 downto 0));
end IP_minmax;

architecture Behavioral of IP_minmax is

component minmax
    Port ( a : in STD_LOGIC_VECTOR(31 downto 0);
           b : in STD_LOGIC_VECTOR(31 downto 0);
           c : in STD_LOGIC_VECTOR(31 downto 0);
           min : out STD_LOGIC_VECTOR(31 downto 0);
           max : out STD_LOGIC_VECTOR(31 downto 0));
end component;

signal min_s, max_s : STD_LOGIC_VECTOR(31 downto 0);
signal a_s, b_s, c_s : STD_LOGIC_VECTOR(31 downto 0);

begin

cmp : minmax port map (a => N2in, b=>Nin, c=>Tin, min=>min_s, max=>max_s);

Nout <= min_s when ipcode= Mycode else (others =>'Z');
Tout <= max_s when ipcode= Mycode else (others =>'Z');

end Behavioral;
