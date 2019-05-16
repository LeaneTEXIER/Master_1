----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/28/2018 10:34:07 AM
-- Design Name: 
-- Module Name: IP_generate_FFFF - Behavioral
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

entity IP_generate_FFFF is
    GENERIC (Mycode : std_logic_vector(10 downto 0) := "00000000000"
);
port (
    IPcode : in std_logic_vector (10 downto 0);
    Tout : out std_logic_vector(31 downto 0)
);
end IP_generate_FFFF;

architecture Behavioral of IP_generate_FFFF is

begin
        
     Tout <= x"00010001" when ipcode= Mycode else (others =>'Z');

end Behavioral;
