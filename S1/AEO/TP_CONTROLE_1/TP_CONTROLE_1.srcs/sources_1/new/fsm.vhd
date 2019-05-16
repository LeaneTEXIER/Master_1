----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 11/07/2018 08:12:19 AM
-- Design Name: 
-- Module Name: alarme - Behavioral
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

entity alarme is
    Port ( clk : in STD_LOGIC;
           reset : in STD_LOGIC;
           porte1 : in STD_LOGIC;
           porte2 : in STD_LOGIC;
           porte3 : in STD_LOGIC;
           ONN : in STD_LOGIC;
           OFF : out STD_LOGIC;
           sirene : out STD_LOGIC;
           delai : out STD_LOGIC_VECTOR (2 downto 0));
end alarme;

architecture Behavioral of alarme is
type state_type is (s_ini, s_alarme, s_eff, s_sirene);
signal state, next_state : state_type;
signal sirene_i, off_i : STD_LOGIC;
signal delai_i : STD_LOGIC_VECTOR(2 downto 0) := "100";
signal second_i : integer := 0;

begin

SYNC_PROC : process (clk, reset, delai_i)
begin
if rising_edge(clk) then
    off <= off_i;
    if (reset = '1') then
        state <= s_ini;
        sirene <= '0';
        delai <= "100";
    else
        state <= next_state;
        sirene <= sirene_i;
        delai <= delai_i;
    end if;
end if;
end process;

OUTPUT_DECODE : process (state, delai_i)
    begin
    case (state) is
    when s_ini =>
        sirene_i <= '0';
        off_i <= '1';
    when s_alarme =>
        sirene_i <= '0';
        off_i <= '0';
    when s_eff =>
        sirene_i <= '0';
        off_i <= '0';
    when others =>
        sirene_i <= '1';
        off_i <= '0';
    end case;
end process;


NEXT_STATE_DECODE : process (ONN, state, porte1, porte2, porte3, clk, second_i, delai_i, reset)
begin
    if(reset='1') then 
           delai_i <= "100";
           second_i <= 0;
    end if;
    if(clk'event and clk='1') then
        if(state = s_eff) then 
            second_i <= second_i + 1;
            if(second_i = 10) then 
                second_i <= 0;
                if(delai_i = "100") then 
                    delai_i <= "011";
                elsif(delai_i = "011") then 
                    delai_i <= "010";
                elsif(delai_i = "010") then 
                    delai_i <= "001";
                else 
                    delai_i <= "000";
                end if;
             end if;
             
        end if;
    end if;
    if (ONN='0') then 
        next_state <= s_ini;
        second_i <= 0;
    else
        case (state) is
            when s_ini =>
                next_state <= s_alarme;
            when s_alarme =>
                if (porte1='1') then 
                    next_state <= s_eff;
                end if;
                if (porte2='1') then 
                    next_state <= s_eff;
                end if;
                if (porte3='1') then 
                    next_state <= s_eff;
                end if;
             when s_eff =>
                if(delai_i = "000") then
                    next_state <= s_sirene;
                else
                    next_state <= s_eff;  
                end if;
             when others =>
                next_state <= state;
         end case;
    end if;
end process; 

end Behavioral;
