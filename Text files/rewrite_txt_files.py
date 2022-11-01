
starting_possible_solutions_file = open("wordle_solutions.txt", "r")
starting_possible_solutions_list = (starting_possible_solutions_file.read()).split("\n")[0:-1]

f = open("wordle_solution_arrays.txt","w")

for word in starting_possible_solutions_list:
    f.write([*word])

f.close()