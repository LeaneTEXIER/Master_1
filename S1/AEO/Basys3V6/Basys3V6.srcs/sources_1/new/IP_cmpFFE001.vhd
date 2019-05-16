----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/28/2018 10:34:07 AM
-- Design Name: 
-- Module Name: IP_cmpFFE001 - Behavioral
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

entity IP_cmpFFE001 is
    GENERIC (Mycode : std_logic_vector(10 downto 0) := "00000000000"
);
port (
    Tin : in std_logic_vector (31 downto 0);
    IPcode : in std_logic_vector (10 downto 0);
    Tout : out std_logic_vector(31 downto 0)
);
end IP_cmpFFE001;

architecture Behavioral of IP_cmpFFE001 is

signal s : std_logic_vector(31 downto 0);

begin

    process(Tin)
        begin
            if(Tin <= x"00FFE001") then 
                s <= x"00000001";
            else
                s <= x"00000000";
            end if;
     end process;
        
     Tout <= s when IPcode = Mycode  else (others =>'Z');

end Behavioral;
