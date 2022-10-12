from statistics import pstdev


starting_possible_solutions_file = open("wordle_solutions.txt", "r")
starting_possible_solutions_list = (starting_possible_solutions_file.read()).split("\n")[0:-1]

possible_guesses_file = open("wordle_guesses.txt", "r")
possible_guesses_list = (possible_guesses_file.read()).split("\n")[0:-1]

known_yellow_letters = []



def assign_word_to_bucket(word, guess):
    letters = [*guess]
    bucket_name = ''
    
    for letter in letters:
        if letter in word:
            bucket_name += '1'
        else:
            bucket_name += '0'

    return bucket_name


def calculate_standard_dev(guess, possible_solutions_list):

    guess = guess.lower()

    buckets = {
        '00000': 0,
        '10000': 0,
        '01000': 0,
        '00100': 0,
        '00010': 0,
        '00001': 0,
        '11000': 0,
        '10100': 0,
        '10010': 0,
        '10001': 0,
        '01100': 0,
        '01010': 0,
        '01001': 0,
        '00110': 0,
        '00101': 0,
        '00011': 0,
        '11100': 0,
        '11010': 0,
        '11001': 0,
        '10110': 0,
        '10101': 0,
        '10011': 0,
        '01110': 0,
        '01101': 0,
        '01011': 0,
        '00111': 0,
        '11110': 0,
        '11101': 0,
        '11011': 0,
        '10111': 0,
        '01111': 0,
        '11111': 0
    }

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
    yellow_letters = [letter for letter in guess if letter in solution]
    gray_letters = [letter for letter in guess if letter not in solution]

    for letter in yellow_letters:
        if letter not in known_yellow_letters:
            known_yellow_letters.append(letter)
    print(f"known yellow letters are {known_yellow_letters}")

    new_possible_solutions_list = reduce_solutions(yellow_letters, gray_letters, possible_solutions_list)
    return new_possible_solutions_list
    # print(f"yellow letters: {yellow_letters}")
    # print(f"gray letters: {gray_letters}")


def reduce_solutions(yellow_letters, gray_letters, possible_solutions_list):
    new_possible_solutions_list = []
    for word in possible_solutions_list:
        if all((letters in word) for letters in yellow_letters) and not (any((letters in word) for letters in gray_letters)):
            new_possible_solutions_list.append(word)
 
    # print(word)
    # print(any((letters in yellow_letters) for letters in word))
    print(f"There are now {len(new_possible_solutions_list)} possible solutions.")
    # print(new_possible_solutions_list)
    return new_possible_solutions_list






wordle_optimizer()