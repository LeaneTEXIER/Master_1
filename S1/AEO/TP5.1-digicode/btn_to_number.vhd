----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/17/2018 09:39:05 AM
-- Design Name: 
-- Module Name: btn_to_number - Behavioral
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

entity btn_to_number is
    Port ( btnR : in STD_LOGIC;
           btnD : in STD_LOGIC;
           btnL : in STD_LOGIC;
           btnU : in STD_LOGIC;
           num : out STD_LOGIC_VECTOR (1 downto 0);
           isP : OUT STD_LOGIC);
end btn_to_number;

architecture Behavioral of btn_to_number is

signal s : STD_LOGIC_VECTOR(2 downto 0);
signal btn : STD_LOGIC_VECTOR(3 downto 0);

begin

    btn <= btnR & btnD & btnL & btnU;
 
    s <= "100" when (btn = "1000") else
         "101" when (btn = "0100") else
         "110" when (btn = "0010") else
         "111" when (btn = "0001") else
         "000";
    num <= s(1 downto 0);
    isP <= s(2);

end Behavioral;
