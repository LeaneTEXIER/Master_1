----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/07/2018 09:23:47 AM
-- Design Name: 
-- Module Name: alarme_top - Behavioral
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

entity alarme_top is
  Port (clk : IN STD_LOGIC;
        btnL, btnD, btnR, btnC : in STD_LOGIC;
        sw: in STD_LOGIC_VECTOR(0 downto 0);
        led :  out STD_LOGIC_VECTOR(1 downto 0)
        );
end alarme_top;

architecture Behavioral of alarme_top is

signal delai_i : STD_LOGIC_VECTOR (2 downto 0);
signal sw_i : STD_LOGIC;

component alarme 
    Port ( clk : in STD_LOGIC;
           reset : in STD_LOGIC;
           porte1 : in STD_LOGIC;
           porte2 : in STD_LOGIC;
           porte3 : in STD_LOGIC;
           ONN : in STD_LOGIC;
           OFF : out STD_LOGIC;
           sirene : out STD_LOGIC;
           delai : out STD_LOGIC_VECTOR (2 downto 0));
end component;

begin

sw_i <= sw(0);

cmp : alarme port map (clk => clk, reset => btnC, porte1=>btnL, porte2=>btnD, porte3=>btnR, ONN=>sw_i, OFF=>led(0), sirene=>led(1), delai=>delai_i);

end Behavioral;
