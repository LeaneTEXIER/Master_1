----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/10/2018 10:31:46 AM
-- Design Name: 
-- Module Name: clk_div - Behavioral
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
use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity clk_div is
    Port ( clk : in STD_LOGIC;
           clk_4hz : inout STD_LOGIC := '0');
end clk_div;

architecture Behavioral of clk_div is

begin

    process(clk)
    variable counter : unsigned(23 downto 0):= (others => '0');
    begin
        if (clk'event and clk='1') then
            counter := counter+1;
            if(counter=X"BEBC20") then
                counter := (others => '0');
                clk_4hz <= NOT clk_4hz;
            end if;
        end if;
    end process;

end Behavioral;
