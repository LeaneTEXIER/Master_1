----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/17/2018 08:53:00 AM
-- Design Name: 
-- Module Name: tp5_q1 - Behavioral
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

entity tp5_q1 is
  Port (clk, reset, x : in STD_LOGIC;
        isEven : out STD_LOGIC );
end tp5_q1;

architecture Behavioral of tp5_q1 is
type state_type is (EVEN, ODD);
signal state, next_state : state_type;
signal isEven_i : std_logic;
begin

SYNC_PROC : process (clk)
begin
if rising_edge(clk) then
    if (reset = '1') then
        state <= EVEN;
        isEven <= '0';
    else
        state <= next_state;
        isEven <= isEven_I;
    end if;
end if;
end process;

OUTPUT_DECODE : process (state)
    begin
    case (state) is
        when EVEN =>
            isEven_I <= '1';
        when ODD =>
            isEven_I <= '0';
        when others =>
            isEven_I <= '0';
    end case;
end process;

NEXT_STATE_DECODE : process (state, x)
begin
    next_state <= EVEN;
    case (state) is
        when EVEN =>
            if (x = '1') then
                next_state <= ODD;
            end if;
        when ODD =>
            if (x = '0') then
                next_state <= ODD;
        end if;
        when others =>
            next_state <= EVEN;
    end case;
end process; 


end Behavioral;
