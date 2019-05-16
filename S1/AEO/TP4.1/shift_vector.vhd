----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/10/2018 09:40:42 AM
-- Design Name: 
-- Module Name: shift_vector - Behavioral
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

entity shift_vector is
    Port ( btnC, btnU, btnL, btnR, btnD : in STD_LOGIC;
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0);
           clk : in  STD_LOGIC);
end shift_vector;

architecture Behavioral of shift_vector is

begin
    
    process(clk)
        variable temp : STD_LOGIC_VECTOR (15 downto 0) := "0000000000000001";
    begin
        if (clk'event and clk='1') then
            temp := temp(14 downto 0) & temp(15);
        end if;
        led <= temp;
    end process;

end Behavioral;
