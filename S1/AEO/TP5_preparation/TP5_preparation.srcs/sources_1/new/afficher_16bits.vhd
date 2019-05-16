----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/24/2018 11:13:34 AM
-- Design Name: 
-- Module Name: afficher_16bits - Behavioral
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

entity afficher_16bits is
  Port (clk : IN STD_LOGIC;
        sw : IN STD_LOGIC_VECTOR (15 downto 0);
        seg : OUT STD_LOGIC_VECTOR (6 downto 0);
        an : OUT STD_LOGIC_VECTOR (3 downto 0));
end afficher_16bits;

architecture Behavioral of afficher_16bits is

signal clockdiv : STD_LOGIC;
signal an_s : STD_LOGIC_VECTOR (3 downto 0);
signal sw_7seg : STD_LOGIC_VECTOR (3 downto 0);

component x7seg
    Port ( sw : in STD_LOGIC_VECTOR (3 downto 0);
           sevenseg : out STD_LOGIC_VECTOR (6 downto 0));
end component;

component clk190
    Port ( clk : in  STD_LOGIC;
           reset : in  STD_LOGIC;
           E190, clk190 : out  STD_LOGIC);
end component;

component fsm_seg
  Port (clk : IN STD_LOGIC;
        an : OUT STD_LOGIC_VECTOR (3 downto 0));
end component;

begin

    cmp_clock : clk190 port map (clk => clk, reset => '0', E190 => open, clk190 => clockdiv);

    cmp_7seg : x7seg port map (sw => sw_7seg, sevenseg => seg);
    
    cmp_fsm : fsm_seg port map (clk => clockdiv, an => an_s);    
    
    with an_s select
        sw_7seg <= sw(3 downto 0) when "1110",
                   sw(7 downto 4) when "1101",
                   sw(11 downto 8) when "1011",
                   sw(15 downto 12) when others;
    an <= an_s;

end Behavioral;
