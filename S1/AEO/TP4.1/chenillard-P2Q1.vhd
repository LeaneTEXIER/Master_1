----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/10/2018 10:52:04 AM
-- Design Name: 
-- Module Name: chenillard - Behavioral
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

entity chenillard is
    Port ( btnC, btnU, btnL, btnR, btnD : in STD_LOGIC;
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0);
           clk : in  STD_LOGIC);
end chenillard;

architecture Behavioral of chenillard is

signal new_clk : std_logic;

component clk_div 
    Port ( clk : in STD_LOGIC;
           clk_4hz : inout STD_LOGIC := '0');
end component;

component shift_vector
    Port ( btnC, btnU, btnL, btnR, btnD : in STD_LOGIC;
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0);
           clk : in  STD_LOGIC);
end component;

begin

    clock : clk_div PORT MAP(clk=> clk, clk_4hz=>new_clk);
    shift_v : shift_vector PORT MAP(btnC=>btnC, btnU=>btnU, btnL=>btnL, btnR=>btnR, btnD=>btnD, sw=>sw, led=>led, clk=>new_clk);

end Behavioral;
