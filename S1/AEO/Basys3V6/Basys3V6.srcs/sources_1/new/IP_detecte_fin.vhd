----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/28/2018 10:34:07 AM
-- Design Name: 
-- Module Name: IP_detecte_fin - Behavioral
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
use ieee.std_logic_unsigned.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity IP_detecte_fin is
    GENERIC (Mycode : std_logic_vector(10 downto 0) := "00000000000"
);
port (
    Tin : in std_logic_vector (31 downto 0);
    IPcode : in std_logic_vector (10 downto 0);
    Tout : out std_logic_vector(31 downto 0)
);
end IP_detecte_fin;

architecture Behavioral of IP_detecte_fin is

signal s : std_logic_vector(31 downto 0);

begin

    process(Tin)
        begin
            if(Tin <= 0) then 
                s <= x"FFFFFFFF";
            else
                s <= x"00000000";
            end if;
     end process;
        
     Tout <= s when IPcode = Mycode  else (others =>'Z');

end Behavioral;
