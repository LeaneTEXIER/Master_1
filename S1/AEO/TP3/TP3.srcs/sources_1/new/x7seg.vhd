----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 09/26/2018 10:00:07 AM
-- Design Name: 
-- Module Name: x7seg - Behavioral
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

entity x7seg is
    Port ( sw : in STD_LOGIC_VECTOR (3 downto 0);
           p : in STD_LOGIC_VECTOR (1 downto 0);
           sevenseg : out STD_LOGIC_VECTOR (6 downto 0));
end x7seg;

architecture Behavioral of x7seg is

begin

    PROCESS(sw, p)
    BEGIN
    
        IF (p = "00") THEN        
             CASE sw IS 
                when "0000" => sevenseg <= "1000000" ;
                when "0001" => sevenseg <= "1111001" ;
                when "0010" => sevenseg <= "0100100" ;
                when "0011" => sevenseg <= "0110000" ;
                when "0100" => sevenseg <= "0011001" ;
                when "0101" => sevenseg <= "0010010" ;
                when "0110" => sevenseg <= "0000010" ;
                when "0111" => sevenseg <= "1111000" ;
                when "1000" => sevenseg <= "0000000" ;
                when "1001" => sevenseg <= "0010000" ;
                when "1010" => sevenseg <= "0001000" ;
                when "1011" => sevenseg <= "0000011" ;
                when "1100" => sevenseg <= "1000110" ;
                when "1101" => sevenseg <= "0100001" ;
                when "1110" => sevenseg <= "0000110" ;
                when "1111" => sevenseg <= "0001110" ;
                when others => sevenseg <= "0000000" ;
              END CASE;
              
           ELSIF (p = "01") THEN 
           -- comparaison
               CASE sw IS
                   when "0000" => sevenseg <= "0001110";
                   when others => sevenseg <= "0000111";
               END CASE;
           
           ELSIF (p = "10") THEN
           -- parity
                CASE sw IS
                    when "0000" => sevenseg <= "0001100";
                    when others => sevenseg <= "1001111";
                END CASE;
           
           ELSE 
               sevenseg <= "0000000";
           END IF;

      END PROCESS;
    
end Behavioral;
