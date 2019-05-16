----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/19/2018 08:02:46 AM
-- Design Name: 
-- Module Name: minmax - Behavioral
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

entity minmax is
    Port ( a : in STD_LOGIC_VECTOR(31 downto 0);
           b : in STD_LOGIC_VECTOR(31 downto 0);
           c : in STD_LOGIC_VECTOR(31 downto 0);
           min : out STD_LOGIC_VECTOR(31 downto 0);
           max : out STD_LOGIC_VECTOR(31 downto 0));
end minmax;

architecture Behavioral of minmax is

begin

    min <= a when (a<b and a<c) else
           b when (b<c) else
           c;
           
    max <= a when (a>b and a>c) else
           b when (b>c) else
           c;

end Behavioral;
