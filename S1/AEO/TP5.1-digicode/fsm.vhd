----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 10/17/2018 09:15:46 AM
-- Design Name: 
-- Module Name: fsm - Behavioral
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

entity fsm is
  Port (clk : IN STD_LOGIC;
        sw : IN STD_LOGIC_VECTOR(7 downto 0);
        btnR, btnD, btnL, btnU, btnC : IN STD_LOGIC;
        led : OUT STD_LOGIC_VECTOR(1 downto 0));
end fsm;

architecture Behavioral of fsm is
type state_type is (s1, s2, s3, s4, sOpen);
signal state, next_state : state_type;
signal sig_i : std_logic_vector(1 downto 0);
signal btn : STD_LOGIC_VECTOR(1 downto 0);
signal btnP : STD_LOGIC;

component btn_to_number
    Port ( btnR : in STD_LOGIC;
           btnD : in STD_LOGIC;
           btnL : in STD_LOGIC;
           btnU : in STD_LOGIC;
           num : out STD_LOGIC_VECTOR (1 downto 0);
           isP : OUT STD_LOGIC);
end component;

begin

SYNC_PROC : process (clk, btnC)
begin
if rising_edge(clk) then
    if (btnC = '1') then
        state <= s1;
        led <= "00";
    else
        state <= next_state;
        led <= sig_i;
    end if;
end if;
end process;

OUTPUT_DECODE : process (state)
    begin
    case (state) is
        when sOpen =>
            sig_i <= "10";
        when s1 =>
            sig_i <= "00";
        when others =>
            sig_i <= "01";
    end case;
end process;

cmp : btn_to_number port map (btnR => btnR, btnD => btnD, btnL => btnL, btnU => btnU, num => btn, isP=>btnP);

NEXT_STATE_DECODE : process (state, sw, btnP, btn)
begin
    next_state <= state;
    if (btnP='1') then 
        case (state) is
            when s1 =>
                if (sw(1 downto 0)=btn) then
                    next_state <= s2;
                else
                    next_state <= s1;
                end if;
            when s2 =>
                if (sw(3 downto 2)=btn) then
                    next_state <= s3;
                else
                    next_state <= s1;
                end if;
            when s3 =>
                if (sw(5 downto 4)=btn) then
                    next_state <= s4;
                else
                    next_state <= s1;
                end if;
            when s4 =>
                if (sw(7 downto 6)=btn) then
                    next_state <= sOpen;
                else
                    next_state <= s1;
                end if;
            when others =>
                next_state <= state;
        end case;
    end if;
end process; 

end Behavioral;
