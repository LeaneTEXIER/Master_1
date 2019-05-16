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
           btnC : in STD_LOGIC;
           btnU : in STD_LOGIC;
           btnD : in STD_LOGIC;
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
           p : in STD_LOGIC_VECTOR (1 downto 0); 
           sevenseg : out STD_LOGIC_VECTOR (6 downto 0));
end component;

component comp
    Port ( a : in STD_LOGIC_VECTOR (7 downto 0);
           b : in STD_LOGIC_VECTOR (7 downto 0);
           res : out STD_LOGIC);
end component;

component parity
    Port ( a : in STD_LOGIC_VECTOR (15 downto 0);
           res : out STD_LOGIC);
end component;

component count1
    Port ( a : in STD_LOGIC_VECTOR (15 downto 0);
           res : out STD_LOGIC_VECTOR (4 downto 0));
end component;

signal sig : std_logic_vector (4 downto 0);
signal s_add, s_and, s_or, s_xor : std_logic_vector(4 downto 0);
signal s_comp, s_parity : std_logic;
signal s_count1 : STD_LOGIC_VECTOR (4 downto 0);
signal btn : std_logic_vector (4 downto 0);
signal combi : STD_LOGIC_VECTOR (7 downto 0);
signal aff : STD_LOGIC_VECTOR (1 downto 0);

begin

    cmp_add : add4 port map (a=>sw(15 downto 12), b=>sw(3 downto 0), sum=>s_add);
    s_and <= ('0' & sw(15 downto 12)) AND ('0' & sw(3 downto 0));
    s_or <= ('0' & sw(15 downto 12)) OR ('0' & sw(3 downto 0));
    s_xor <= ('0' & sw(15 downto 12)) XOR ('0' & sw(3 downto 0));
    cmp_comp : comp port map (a=>sw(15 downto 8), b=>sw(7 downto 0), res=>s_comp);
    cmp_parity : parity port map (a=>sw, res=>s_parity);
    cmp_count1 : count1 port map (a=>sw, res=>s_count1);
        
    
    btn <= btnU & btnC & btnD & btnL & btnR;

    with btn select 
        sig <= s_add when "00000",
               s_and when "00001",
               s_or when "00010",
               s_xor when "00011",
               
               "0000" & s_comp when "00100",
               "0000" & s_comp when "00101",
               "0000" & s_comp when "00110",
               "0000" & s_comp when "00111",
               
               "0000" & s_parity when "01000",
               "0000" & s_parity when "01001",
               "0000" & s_parity when "01010",
               "0000" & s_parity when "01011",
               
               s_count1 when "10000",
               s_count1 when "10001",
               s_count1 when "10010",
               s_count1 when "10011",
               
               "00000" when others;
    
    with btn select
        aff <= "01" when "00100",
               "01" when "00101",
               "01" when "00110",
               "01" when "00111",
        
               "10" when "01000",
               "10" when "01001",
               "10" when "01010",
               "10" when "01011",               
               
               
               "00" when "00000",
               "00" when "00001",
               "00" when "00010",
               "00" when "00011",
               
               "00" when "10000",
               "00" when "10001",
               "00" when "10010",
               "00" when "10011",           
               
               "11" when others;
               
    cmp_x7seg : x7seg port map (sw=>sig(3 downto 0), p=>aff, sevenseg=>seg);
    led <= "00000000000" & sig;
    an <= x"e";
    
end Behavioral;
