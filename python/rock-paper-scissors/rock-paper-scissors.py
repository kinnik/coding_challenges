import random

SCORE  = 0;
WINS   = 0;
LOSSES = 0;
DRAWS  = 0;
CHOICE_TO_NUM = {'R': 0, 'S': 1, 'P': 2, 'L': 3, 'X': 4}
NUM_TO_NAME   = {0: 'Rock', 1: 'Spock', 2: 'Paper', 3: 'Lizard', 4: 'Scissors'}

def number_to_name(number):
    global NUM_TO_NAME
    return NUM_TO_NAME[number]
    
def choice_to_number(choice):
    global CHOICE_TO_NUM
    return CHOICE_TO_NUM[choice]

def play(player_number):
    global WINS, LOSSES, DRAWS, SCORE
    comp_number = random.randrange(0,5)
    
    # compute the difference between player_number and comp_number modulo five
    diff = (comp_number - player_number) % 5

    if (diff == 1 or diff == 2):
        result = 'Computer wins!'
        LOSSES += 1
    elif (diff == 3 or diff == 4):
        result = 'You win!'
        WINS += 1
    else:
        result = "It's a tie!"
        DRAWS += 1

    # convert comp_number to name using number_to_name
    comp_name = number_to_name(comp_number)
    
    # print results
    print ''
    print 'You played', number_to_name(player_number)
    print 'Computer played', number_to_name(comp_number)
    print result
    print_score()

def print_score():
    print 'Score:', calculate_score(WINS, LOSSES), '( Wins:', WINS, '| Losses:', LOSSES, '| Draws:', DRAWS, ')'
    print ''

def calculate_score(num_wins, num_losses):
    return num_wins - num_losses

def print_rules():
    print 'The rules of rock-paper-scissors-lizard-Spock are:'
    print '* Scissors cut Paper'
    print '* Paper covers Rock'
    print '* Rock crushes Lizard'
    print '* Lizard poisons Spock'
    print '* Spock smashes Scissors'
    print '* Scissors decapitate Lizard'
    print '* Lizard eats Paper'
    print '* Paper disproves Spock'
    print '* Spock vaporizes Rock'
    print '* Rock crushes Scissors'
    print ''

def print_banner():
    print "###################################"
    print "# ROCK-PAPER-SCISSOR-LIZARD-SPOCK #"
    print "###################################"

def get_instructions():
    return 'Type one of the letters in the bracket and press Enter:\n* Rock    [R]\n* Paper   [P]\n* Scissor [X]\n* Lizard  [L]\n* Spock   [S]\n\n* Help    [H]\n* Quit    [Q]\n> '
 
def run():
    print_banner()
    previous_move_valid = True
    while True:
        if previous_move_valid:
            display_text = 'Make your move. ' + get_instructions()
        else:
            display_text = 'Invalid move. ' + get_instructions()
        
        choice = raw_input(display_text)
        choice = choice[0].strip().upper()

        if choice == 'Q':
            break;
        elif choice == 'H':
            print_rules()
            previous_move_valid = True
        else: 

            try:
                choice_num = choice_to_number(choice)
                play(choice_num)
                previous_move_valid = True
            except KeyError: 
                previous_move_valid = False

    print "Good bye! Thank you for playing."
    print_score()

run()
