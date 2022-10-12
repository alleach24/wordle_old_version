from codecs import getincrementaldecoder
from statistics import pstdev


starting_possible_solutions_file = open("wordle_solutions.txt", "r")
starting_possible_solutions_list = (starting_possible_solutions_file.read()).split("\n")[0:-1]

possible_guesses_file = open("wordle_guesses.txt", "r")
possible_guesses_list = (possible_guesses_file.read()).split("\n")[0:-1]

known_gray_letters = []
known_yellow_letters = []
known_green_letters = ['.','.','.','.','.']



def assign_word_to_bucket(solution_word, guess):
    guess_letters = [*guess]
    bucket_name = ''
    
    for letter in guess_letters:
        if letter not in solution_word:
            bucket_name += '0'
        elif solution_word[guess_letters.index(letter)] == letter:
            bucket_name += '2'
            known_green_letters[guess_letters.index(letter)] = letter
        else:
            bucket_name += '1'
            # known_yellow_letters.append(letter)

    return bucket_name


def calculate_standard_dev(guess, possible_solutions_list):

    guess = guess.lower()

    buckets = {'00000': 0, '00001': 0, '00002': 0, '00010': 0, '00011': 0, '00012': 0, 
    '00020': 0, '00021': 0, '00022': 0, '00100': 0, '00101': 0, '00102': 0, '00110': 0, 
    '00111': 0, '00112': 0, '00120': 0, '00121': 0, '00122': 0, '00200': 0, '00201': 0, 
    '00202': 0, '00210': 0, '00211': 0, '00212': 0, '00220': 0, '00221': 0, '00222': 0, 
    '01000': 0, '01001': 0, '01002': 0, '01010': 0, '01011': 0, '01012': 0, '01020': 0, 
    '01021': 0, '01022': 0, '01100': 0, '01101': 0, '01102': 0, '01110': 0, '01111': 0, 
    '01112': 0, '01120': 0, '01121': 0, '01122': 0, '01200': 0, '01201': 0, '01202': 0, 
    '01210': 0, '01211': 0, '01212': 0, '01220': 0, '01221': 0, '01222': 0, '02000': 0, 
    '02001': 0, '02002': 0, '02010': 0, '02011': 0, '02012': 0, '02020': 0, '02021': 0, 
    '02022': 0, '02100': 0, '02101': 0, '02102': 0, '02110': 0, '02111': 0, '02112': 0, 
    '02120': 0, '02121': 0, '02122': 0, '02200': 0, '02201': 0, '02202': 0, '02210': 0, 
    '02211': 0, '02212': 0, '02220': 0, '02221': 0, '02222': 0, '10000': 0, '10001': 0, 
    '10002': 0, '10010': 0, '10011': 0, '10012': 0, '10020': 0, '10021': 0, '10022': 0, 
    '10100': 0, '10101': 0, '10102': 0, '10110': 0, '10111': 0, '10112': 0, '10120': 0, 
    '10121': 0, '10122': 0, '10200': 0, '10201': 0, '10202': 0, '10210': 0, '10211': 0, 
    '10212': 0, '10220': 0, '10221': 0, '10222': 0, '11000': 0, '11001': 0, '11002': 0, 
    '11010': 0, '11011': 0, '11012': 0, '11020': 0, '11021': 0, '11022': 0, '11100': 0, 
    '11101': 0, '11102': 0, '11110': 0, '11111': 0, '11112': 0, '11120': 0, '11121': 0, 
    '11122': 0, '11200': 0, '11201': 0, '11202': 0, '11210': 0, '11211': 0, '11212': 0, 
    '11220': 0, '11221': 0, '11222': 0, '12000': 0, '12001': 0, '12002': 0, '12010': 0, 
    '12011': 0, '12012': 0, '12020': 0, '12021': 0, '12022': 0, '12100': 0, '12101': 0, 
    '12102': 0, '12110': 0, '12111': 0, '12112': 0, '12120': 0, '12121': 0, '12122': 0, 
    '12200': 0, '12201': 0, '12202': 0, '12210': 0, '12211': 0, '12212': 0, '12220': 0, 
    '12221': 0, '12222': 0, '20000': 0, '20001': 0, '20002': 0, '20010': 0, '20011': 0, 
    '20012': 0, '20020': 0, '20021': 0, '20022': 0, '20100': 0, '20101': 0, '20102': 0, 
    '20110': 0, '20111': 0, '20112': 0, '20120': 0, '20121': 0, '20122': 0, '20200': 0, 
    '20201': 0, '20202': 0, '20210': 0, '20211': 0, '20212': 0, '20220': 0, '20221': 0, 
    '20222': 0, '21000': 0, '21001': 0, '21002': 0, '21010': 0, '21011': 0, '21012': 0, 
    '21020': 0, '21021': 0, '21022': 0, '21100': 0, '21101': 0, '21102': 0, '21110': 0, 
    '21111': 0, '21112': 0, '21120': 0, '21121': 0, '21122': 0, '21200': 0, '21201': 0, 
    '21202': 0, '21210': 0, '21211': 0, '21212': 0, '21220': 0, '21221': 0, '21222': 0, 
    '22000': 0, '22001': 0, '22002': 0, '22010': 0, '22011': 0, '22012': 0, '22020': 0, 
    '22021': 0, '22022': 0, '22100': 0, '22101': 0, '22102': 0, '22110': 0, '22111': 0, 
    '22112': 0, '22120': 0, '22121': 0, '22122': 0, '22200': 0, '22201': 0, '22202': 0, 
    '22210': 0, '22211': 0, '22212': 0, '22220': 0, '22221': 0, '22222': 0}

    total_guesses = len(possible_solutions_list)
    for possible_solution in possible_solutions_list:
        bucket = assign_word_to_bucket(possible_solution,guess)
        buckets[bucket] += (1/total_guesses * 100)

    std_dev = pstdev(list(buckets.values()))
    return std_dev


def evaluate_all_words(possible_guesses_list, possible_solutions_list):
    all_standard_devs = {}
    for word in possible_guesses_list:
        std_dev = calculate_standard_dev(word, possible_solutions_list)
        all_standard_devs[word] = std_dev

    min_std_dev = min([*all_standard_devs.values()])
    # print(all_standard_devs)
    # print(min_std_dev)
    optimal_guesses = []
    for word in possible_guesses_list:
        if all_standard_devs[word] == min_std_dev:
            optimal_guesses.append(word)
    return optimal_guesses, min_std_dev


def wordle_optimizer():
    print("Welcome to the wordle optimizer! Let's optimize a word.")
    word_to_be_optimized = input("What word would you like to optimize? ").lower()
    print(f'Optimizing "{word_to_be_optimized}"')
    print('...')
    print(f"There are currently {len(starting_possible_solutions_list)} possible solutions.")
    print(f"The optimal word(s) to guess are ['irate', 'retia', 'terai'] with a standard deviation of 2.443126656651582")
    word_to_be_guessed = input("Which word would you like to guess? ").lower()
    new_possible_solutions_list = evaluate(word_to_be_optimized, word_to_be_guessed, starting_possible_solutions_list)

    optimize(word_to_be_optimized, new_possible_solutions_list)
    

def optimize(word_to_be_optimized, possible_solutions_list):
    
    ## random placeholder for actual recursive check
    if (len(known_yellow_letters) >= 5) or len(possible_solutions_list) <= 2:
        return possible_solutions_list

    (optimal_guesses, std_dev) = evaluate_all_words(possible_guesses_list, possible_solutions_list)
    print(f"The optimal word(s) to guess are {optimal_guesses} with a standard deviation of {std_dev}")
    word_to_be_guessed = input("Which word would you like to guess? ").lower()
    new_possible_solutions_list = evaluate(word_to_be_optimized, word_to_be_guessed, possible_solutions_list)
    i = 0
    
    return optimize(word_to_be_optimized, new_possible_solutions_list)



def evaluate(solution, guess, possible_solutions_list):
    print(f'Evaluating the guess "{guess}"')
    print("...")

    green_letters = []
    gray_letters = []
    yellow_letters = []
    for letter in guess:
        if letter not in solution:
            gray_letters.append(letter)
            green_letters.append('.')
        elif solution[guess.index(letter)] == letter:
            green_letters.append(letter)
        else:
            green_letters.append('.')
            yellow_letters.append(letter)
        # yellow_letters = [letter for letter in guess if letter in solution]
        # gray_letters = [letter for letter in guess if letter not in solution]

    for letter in green_letters:
        if letter not in known_green_letters:
            known_green_letters[guess.index(letter)] = letter
    print(f"known green letters are {known_green_letters}")
    for letter in yellow_letters:
        if letter not in known_yellow_letters:
            known_yellow_letters.append(letter)
    print(f"known yellow letters are {known_yellow_letters}")
    for letter in gray_letters:
        if letter not in known_gray_letters:
            known_gray_letters.append(letter)
    print(f"known gray letters are {known_gray_letters}")

    # print(green_letters)
    new_possible_solutions_list = reduce_solutions(green_letters, yellow_letters, gray_letters, possible_solutions_list)
    return new_possible_solutions_list
    # print(f"yellow letters: {yellow_letters}")
    # print(f"gray letters: {gray_letters}")


def reduce_solutions(green_letters, yellow_letters, gray_letters, possible_solutions_list):
    new_possible_solutions_list = []
    # for word in possible_solutions_list:
    #     if all((letters in word) for letters in yellow_letters) and not (any((letters in word) for letters in gray_letters)):
    #         new_possible_solutions_list.append(word)
    # print(green_letters)
    for word in possible_solutions_list:
# ##########################
#         # word = possible_solutions_list[index]
#         if (not (any((letters in word) for letters in gray_letters))) and (all((letters in word) for letters in yellow_letters)):
#             # possible_solutions_list.remove(word)
#             new_possible_solutions_list.append(word)
#         # elif all((letters in word) for letters in yellow_letters):
#         #     # possible_solutions_list.remove(word)
#         #     new_possible_solutions_list.append(word)
#         else:
#             for i in [0,1,2,3,4]:
#                 # print(green_letters)
#                 # print(i)
#                 if not green_letters[i] == '.':
#                     if word[i] == green_letters[i]:
#                         # print(word)
#                         # possible_solutions_list.remove(word)
#                         new_possible_solutions_list.append(word)
# #######################################################
        green_match = True
        if (not (any((letters in word) for letters in gray_letters))) and (all((letters in word) for letters in yellow_letters)):
            for i in [0,1,2,3,4]:
                if not green_letters[i] == '.':
                    if word.find(green_letters[i]) != i:
                        green_match = False
            if green_match:
                new_possible_solutions_list.append(word)



    print(new_possible_solutions_list)
    # print(word)
    # print(any((letters in yellow_letters) for letters in word))
    print(f"There are now {len(new_possible_solutions_list)} possible solutions.")
    # print(new_possible_solutions_list)
    return new_possible_solutions_list






wordle_optimizer()