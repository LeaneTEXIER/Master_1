----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/10/2018 09:01:05 AM
-- Design Name: 
-- Module Name: shift - Behavioral
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

entity shift is
    Port ( btn : in STD_LOGIC_VECTOR (4 downto 0);
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0);
           clk : in  STD_LOGIC);
end shift;

architecture Behavioral of shift is

signal Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15 : std_logic;

component fpd 
    GENERIC (init_value: STD_Logic  := '0');
    Port ( d, clk : in  STD_LOGIC;
           q : out  STD_LOGIC:= init_value);
end component;

begin

    d0: fpd GENERIC MAP(init_value => '1') PORT MAP(d => Q15,q => Q0, clk => clk);
    d1: fpd PORT MAP(d=> Q0, q=>Q1, clk=>clk);
    d2: fpd PORT MAP(d=> Q1, q=>Q2, clk=>clk);
    d3: fpd PORT MAP(d=> Q2, q=>Q3, clk=>clk);
    d4: fpd PORT MAP(d=> Q3, q=>Q4, clk=>clk);
    d5: fpd PORT MAP(d=> Q4, q=>Q5, clk=>clk);
    d6: fpd PORT MAP(d=> Q5, q=>Q6, clk=>clk);
    d7: fpd PORT MAP(d=> Q6, q=>Q7, clk=>clk);
    d8: fpd PORT MAP(d=> Q7, q=>Q8, clk=>clk);
    d9: fpd PORT MAP(d=> Q8, q=>Q9, clk=>clk);
    d10: fpd PORT MAP(d=> Q9, q=>Q10, clk=>clk);
    d11: fpd PORT MAP(d=> Q10, q=>Q11, clk=>clk);
    d12: fpd PORT MAP(d=> Q11, q=>Q12, clk=>clk);
    d13: fpd PORT MAP(d=> Q12, q=>Q13, clk=>clk);
    d14: fpd PORT MAP(d=> Q13, q=>Q14, clk=>clk);
    d15: fpd PORT MAP(d=> Q14, q=>Q15, clk=>clk);
    
    led<= Q15&Q14&Q13&Q12&Q11&Q10&Q9&Q8&Q7&Q6&Q5&Q4&Q3&Q2&Q1&Q0;

end Behavioral;
