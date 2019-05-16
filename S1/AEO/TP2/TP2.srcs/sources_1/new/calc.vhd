----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 09/26/2018 10:23:35 AM
-- Design Name: 
-- Module Name: calc - Behavioral
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

entity calc is
    Port ( sw : in STD_LOGIC_VECTOR (15 downto 0);
           btnR : in STD_LOGIC;
           btnL : in STD_LOGIC;
           led : out STD_LOGIC_VECTOR (15 downto 0);
           seg : out STD_LOGIC_VECTOR (6 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0));
end calc;

architecture Behavioral of calc is

component add4
    Port ( a : in STD_LOGIC_VECTOR (3 downto 0);
           b : in STD_LOGIC_VECTOR (3 downto 0);
           sum : out STD_LOGIC_VECTOR (4 downto 0));
end component;

component x7seg 
    Port ( sw : in STD_LOGIC_VECTOR (3 downto 0);
           sevenseg : out STD_LOGIC_VECTOR (6 downto 0));
end component;

signal sig : std_logic_vector (4 downto 0);
signal s_add, s_and, s_or, s_xor : std_logic_vector(4 downto 0);
signal btn : std_logic_vector (1 downto 0);

begin

    cmp_add : add4 port map (a=>sw(15 downto 12), b=>sw(3 downto 0), sum=>s_add);
    s_and <= ('0' & sw(15 downto 12)) AND ('0' & sw(3 downto 0));
    s_or <= ('0' & sw(15 downto 12)) OR ('0' & sw(3 downto 0));
    s_xor <= ('0' & sw(15 downto 12)) XOR ('0' & sw(3 downto 0));
    btn <= btnL & btnR;

    with btn select 
        sig <= s_add when "00",
               s_and when "01",
               s_or when "10",
               s_xor when "11",
               "00000" when others;
               
    cmp_x7seg : x7seg port map (sw=>sig(3 downto 0), sevenseg=>seg);
    led <= "00000000000" & sig;
    an <= x"e";
    
end Behavioral;
