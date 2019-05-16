----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/03/2018 09:47:56 AM
-- Design Name: 
-- Module Name: count1 - Behavioral
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
use ieee.std_logic_arith.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity count1 is
    Port ( a : in STD_LOGIC_VECTOR (15 downto 0);
           res : out STD_LOGIC_VECTOR (4 downto 0));
end count1;

architecture Behavioral of count1 is

begin

    PROCESS(a)
    VARIABLE nombre_1 : INTEGER;
    BEGIN
        nombre_1 := 0;
        FOR I IN 0 TO 15 LOOP
            IF a(I) = '1' THEN
                nombre_1 := nombre_1 + 1;
            END IF;
        END LOOP;
        res <= conv_std_logic_vector(nombre_1, 5);
      END PROCESS;

end Behavioral;
