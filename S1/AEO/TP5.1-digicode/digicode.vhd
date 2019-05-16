----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/17/2018 11:47:16 AM
-- Design Name: 
-- Module Name: digicode - Behavioral
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

entity digicode is
  Port (clk : IN STD_LOGIC;
       sw : IN STD_LOGIC_VECTOR(7 downto 0);
       btnR, btnD, btnL, btnU, btnC : IN STD_LOGIC;
       led : OUT STD_LOGIC_VECTOR(1 downto 0));
end digicode;

architecture Behavioral of digicode is

signal E190, clockdiv : STD_LOGIC;
signal outBtnR, outBtnD, outBtnL, outBtnU, outBtnC : STD_LOGIC;

component btn_pulse
    Port ( inp : in STD_LOGIC;
           E : in STD_LOGIC;
           clk : in STD_LOGIC;
           outp : out STD_LOGIC);
end component;

component clkdiv
    Port ( clk : in  STD_LOGIC;
           reset : in  STD_LOGIC;
           E190, clk190 : out  STD_LOGIC);
end component;

component fsm
    Port (clk : IN STD_LOGIC;
        sw : IN STD_LOGIC_VECTOR(7 downto 0);
        btnR, btnD, btnL, btnU, btnC : IN STD_LOGIC;
        led : OUT STD_LOGIC_VECTOR(1 downto 0));
end component;


begin

    cmp_clock : clkdiv port map (clk => clk, reset => '0', E190 => E190, clk190 => clockdiv);
    
    cmp_btnR : btn_pulse port map (inp => btnR, E => E190, clk => clockdiv, outp => outBtnR); 
    cmp_btnD : btn_pulse port map (inp => btnD, E => E190, clk => clockdiv, outp => outBtnD); 
    cmp_btnL : btn_pulse port map (inp => btnL, E => E190, clk => clockdiv, outp => outBtnL); 
    cmp_btnU : btn_pulse port map (inp => btnU, E => E190, clk => clockdiv, outp => outBtnU); 
    cmp_btnC : btn_pulse port map (inp => btnC, E => E190, clk => clockdiv, outp => outBtnC); 
    
    cmp_fsm : fsm port map (clk => clockdiv, sw => sw, btnR => outBtnR, btnD => outBtnD, btnL => outBtnL, btnU => outBtnU, btnC => outBtnC, led => led);

end Behavioral;
