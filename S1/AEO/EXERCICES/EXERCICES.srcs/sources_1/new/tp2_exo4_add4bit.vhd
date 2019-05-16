----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 09/26/2018 09:33:14 AM
-- Design Name: 
-- Module Name: tp2_exo4_add4bit - Behavioral
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

entity tp2_exo4_add4bit is
  Port ( a : in STD_LOGIC_VECTOR (3 downto 0);
         b : in STD_LOGIC_VECTOR (3 downto 0);
         s : out STD_LOGIC_VECTOR (3 downto 0);
         cin : in STD_LOGIC;
         cout : out STD_LOGIC);
end tp2_exo4_add4bit;

architecture Behavioral of tp2_exo4_add4bit is

component add1bit
    Port ( a, b, cin : in STD_LOGIC;
           s, cout : out STD_LOGIC);
end component;

signal c1,c2,c3 : std_logic;

begin

    inst0 : add1bit port map (a=>a(0), b=>b(0), cin=>cin, s=>s(0), cout=>c1); 
    inst1 : add1bit port map (a=>a(1), b=>b(1), cin=>c1, s=>s(1), cout=>c2); 
    inst2 : add1bit port map (a=>a(2), b=>b(2), cin=>c2, s=>s(2), cout=>c3); 
    inst3 : add1bit port map (a=>a(3), b=>b(3), cin=>c3, s=>s(3), cout=>cout); 

end Behavioral;
