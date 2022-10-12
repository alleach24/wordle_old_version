possible_solutions_file = open("wordle_solutions.txt", "r")
possible_solutions_list = (possible_solutions_file.read()).split("\n")[0:-1]

possible_guesses_file = open("wordle_guesses.txt", "r")
possible_guesses_list = (possible_guesses_file.read()).split("\n")[0:-1]



def assign_word_to_bucket(word, starting_word):
    letters = [*starting_word]
    bucket_name = ''
    
    for letter in letters:
        if letter in word:
            bucket_name += '1'
        else:
            bucket_name += '0'

    return bucket_name


def standard_dev_equation(buckets):
    percentages = list(buckets.values())
    mean = sum(percentages) / len(percentages)

    difference_squared = []
    for num in percentages:
        difference_squared.append((num - mean)**2)

    variance = sum(difference_squared)/len(difference_squared)

    std_dev = variance ** (1/2)

    return std_dev


def calculate_standard_dev(starting_word):

    starting_word = starting_word.lower()

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

    for possible_solution in possible_solutions_list:
        bucket = assign_word_to_bucket(possible_solution,starting_word)
        buckets[bucket] += 1

    total_guesses = len(possible_solutions_list)
    for each_bucket in buckets:
        buckets[each_bucket] = buckets[each_bucket]/total_guesses * 100

    std_dev = standard_dev_equation(buckets)
    return std_dev


def evaluate_all_words(possible_guesses_list):
    all_standard_devs = {}
    for word in possible_guesses_list:
        std_dev = calculate_standard_dev(word)
        all_standard_devs[word] = std_dev
        # print(word)
        # if std_dev <= 2.445:
            # print(f"The standard deviation for {word} is {std_dev}%.")
    # print(all_standard_devs)
    # print([*all_standard_devs.values()])
    min_std_dev = min([*all_standard_devs.values()])
    # print(min_std_dev)
    optimal_guesses = []
    for word in possible_guesses_list:
        if all_standard_devs[word] == min_std_dev:
            optimal_guesses.append(word)
    return optimal_guesses

print(evaluate_all_words(possible_guesses_list))