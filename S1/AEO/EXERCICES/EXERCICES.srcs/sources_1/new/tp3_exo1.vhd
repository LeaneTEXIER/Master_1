----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/03/2018 08:30:05 AM
-- Design Name: 
-- Module Name: tp3_exo1 - Behavioral
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

entity tp3_exo1 is
  Port ( 
    a : IN STD_LOGIC;
    b : IN STD_LOGIC;
    c : IN STD_LOGIC;
    d : IN STD_LOGIC;
    adr : IN STD_LOGIC_VECTOR (1 downto 0);
    s : OUT STD_LOGIC
   );
end tp3_exo1;

architecture Behavioral of tp3_exo1 is

begin

    PROCESS (a, b, c, d, adr)
    BEGIN
        case adr IS
            WHEN "00" => s <= a;
            WHEN "01" => s <= b;
            WHEN "10" => s <= c;
            WHEN others => s <= d;
         END CASE;    
    END PROCESS;
    
end Behavioral;


--Process(sw)
--BEGIN
--CASE sw IS
--when x"0" => sevenseg <= "1000000"

-- Q3.a. Pas tout le temps de valeur pour F et G!!!