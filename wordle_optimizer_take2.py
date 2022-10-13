from statistics import pstdev


starting_possible_solutions_file = open("wordle_solutions.txt", "r")
starting_possible_solutions_list = (starting_possible_solutions_file.read()).split("\n")[0:-1]

possible_guesses_file = open("wordle_guesses.txt", "r")
possible_guesses_list = (possible_guesses_file.read()).split("\n")[0:-1]

guess_being_evaluated = "cigar" #possible_guesses_list[0]
print(f"guess being evaluated is {guess_being_evaluated}")

for possible_solution in starting_possible_solutions_list:
    bucket_name = ['4','4','4','4','4']
    possible_solution_letters = [*possible_solution]
    guess_letters = [*guess_being_evaluated]
    # print(guess_letters)
    print(f"possible solution letters are {possible_solution_letters}")
    # for letter in possible_solution_letters:
    for i in [0,1,2,3,4]:
        # letter = guess_letters[i]
        if possible_solution_letters[i] == guess_letters[i]:
            bucket_name[i] = '2'
        elif possible_solution_letters[i] not in guess_being_evaluated:
            bucket_name[i] = '0'
        elif possible_solution_letters[i]
        # elif 
        
        # elif letter not in possible_solution_letters:
        #     bucket_name += '0'
        # else: 
        #     bucket_name += '4'



        # letter = possible_solution_letters[i]
        # # print(letter)
        # # print(guess_being_evaluated)
        # if letter not in guess_being_evaluated:
        #     bucket_name += '0'
        # elif guess_being_evaluated[i] == letter: #green:
        #     bucket_name += '2'
        # elif possible_solution_letters.count(letter) == 1: 
        #     bucket_name += '1'
        # elif: #have to account for double letters somehow

        # else: 
        #     bucket_name += '4'
    print(bucket_name)

