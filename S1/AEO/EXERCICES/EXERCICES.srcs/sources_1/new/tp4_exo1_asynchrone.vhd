----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/10/2018 08:41:58 AM
-- Design Name: 
-- Module Name: tp4_exo1_asynchrone - Behavioral
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

entity tp4_exo1_asynchrone is
    Port ( reset : in STD_LOGIC;
           clk : in STD_LOGIC;
           D : in STD_LOGIC;
           Q : out STD_LOGIC);
end tp4_exo1_asynchrone;

architecture Behavioral of tp4_exo1_asynchrone is

begin

    process (clk, reset)
    begin
        if (reset='1') then 
            Q <= '0';
        elsif (clk'event and clk='1') then
            Q <= D;
        end if;
    end process ;

end Behavioral;
