----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/24/2018 10:57:44 AM
-- Design Name: 
-- Module Name: fsm_seg - Behavioral
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

entity fsm_seg is
  Port (clk : IN STD_LOGIC;
        an : OUT STD_LOGIC_VECTOR (3 downto 0));
end fsm_seg;

architecture Behavioral of fsm_seg is

type state_type is (st1, st2, st3, st4); 
signal anodes_s : STD_LOGIC_VECTOR (3 downto 0);
signal state, next_state : state_type; 

begin

SYNC_PROC : process (clk)
begin
    if rising_edge(clk) then
        state <= next_state;
        an <= anodes_s;
    end if;
end process;

OUTPUT_DECODE: process (state)
begin
    if (state = st1) then
        anodes_s <= "1110";
    elsif (state = st2) then
        anodes_s <= "1101";
    elsif (state = st3) then
        anodes_s <= "1011";
    else
        anodes_s <= "0111";
    end if;
end process;

NEXT_STATE_DECODE: process (state)
begin
    next_state <= state;
    case (state) is
        when st1 =>
            next_state <= st2;
        when st2 =>
            next_state <= st3;
        when st3 =>
            next_state <= st4;
        when st4 =>
            next_state <= st1;
        when others =>
            next_state <= st1;
      end case;      
   end process;

end Behavioral;
