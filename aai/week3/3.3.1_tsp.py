import numpy as np
# import matplotlib.pyplot as plt
import random


def read_matrix(file_path):
    with open(file_path, 'r') as file:
        matrix = []
        for line in file:
            row = list(map(float, line.replace('\n', '').split(',')))
            matrix.append(row)
    return np.array(matrix)


def tsp_random(matrix):
    """
    Generates a random path for the Traveling Salesman Problem (TSP).

    Parameters:
    matrix (numpy.ndarray): 2D numpy array representing the distance matrix between cities.

    Returns:
    tuple: A tuple containing the random path and the total distance of the path.
    """
    n = matrix.shape[0]  # Number of cities
    path = list(range(n))  # Create a list of cities [0, 1, 2, ..., n-1]
    random.shuffle(path)  # Shuffle the list to generate a random path

    # Add the starting city to the end of the path to complete the cycle
    path.append(path[0])

    # Calculate the total distance of this random path
    total_distance = calculate_total_distance(matrix, path)

    return path, total_distance

def tsp_greedy(matrix):
    """
    Solves the Traveling Salesman Problem (TSP) using a greedy algorithm.

    Parameters:
    matrix (numpy.ndarray): 2D numpy array representing the distance matrix between cities.

    Returns:
    tuple: A tuple containing the best path and the total distance of the path.
    """
    n = matrix.shape[0]  # Number of cities
    unvisited = set(range(n))  # Initialize the set of unvisited cities
    path = [0]  # Start from city 0 (you can choose any city as the starting point)
    unvisited.remove(0)  # Mark city 0 as visited
    total_distance = 0  # Initialize the total travel distance

    while unvisited:
        last_city = path[-1]  # Get the last visited city
        # Find the nearest unvisited city
        next_dist = -1
        next_city = -1
        for current_city in unvisited:
            dist = matrix[last_city][current_city]
            if next_dist == -1:
                next_dist = dist
                next_city = current_city
            elif dist < next_dist:
                next_dist = dist
                next_city = current_city
        # next_city = min(unvisited, key=lambda city: matrix[last_city][city])
        # print(f"{last_city} to {next_city}: {matrix[last_city][next_city]}")
        path.append(next_city)  # Add the nearest city to the path
        total_distance += matrix[last_city][next_city]  # Add the distance to the total distance
        unvisited.remove(next_city)  # Mark the city as visited

    # Return to the starting city to complete the cycle
    total_distance += matrix[path[-1]][path[0]]  # Add the distance back to the starting city
    path.append(0)  # Add the starting city to complete the cycle

    return path, total_distance


def calculate_total_distance(matrix, path):
    """
    Calculates the total distance of a given TSP path.

    Parameters:
    matrix (numpy.ndarray): 2D numpy array representing the distance matrix between cities.
    path (list): List of cities representing the path.

    Returns:
    float: Total distance of the path.
    """
    total_distance = 0
    # print(path)
    for i in range(len(path) - 1):
        from_pt = path[i]
        to_pt = path[i + 1]
        # print(f"{from_pt} to {to_pt} = {matrix[from_pt][to_pt]}")
        total_distance += matrix[from_pt][to_pt]
    return total_distance


def swap_adjacent_cities(path):
    """
    Pick a city in path randomly, then performs a swap of two adjacent cities in the path.

    Parameters:
    path (list): List of cities representing the path.

    Returns:
    list: A new path with the adjacent cities swapped.
    """
    new_path = path.copy()
    # choose a random city index
    i = random.randint(0, len(new_path) - 2)
    # Swap city at index i with the next city (i+1)
    # print(f"city {new_path[i]} was swapped with city {new_path[i + 1]}.")
    new_path[i], new_path[i + 1] = new_path[i + 1], new_path[i]
    return new_path


def hill_climbing(distance_matrix, initial_path, max_no_improvement=25):
    """
    Perform hill climbing algorithm to swap two adjacent cities to try to improve the cost of travel at maximum 25 times.
    :param distance_matrix: matrix table
    :param max_no_improvement: 25 times as default
    :return:
    """
    n = distance_matrix.shape[0]

    current_tour = initial_path.copy()
    current_cost = calculate_total_distance(distance_matrix, current_tour)

    best_tour = current_tour
    best_cost = current_cost

    no_improvement = 0
    cost_history = [current_cost]

    no_experimented = 0
    # Iterative improvement process
    while no_improvement < max_no_improvement:
        # Generate a neighboring solution
        new_tour = swap_adjacent_cities(best_tour)
        new_cost = calculate_total_distance(distance_matrix, new_tour)
        # print(f"old: {best_cost}:  {best_tour}")
        # print(f"new: {new_cost}: {new_tour}")

        # If the new solution is better, accept it
        if new_cost < current_cost:
            current_tour = new_tour
            current_cost = new_cost
            cost_history.append(current_cost)
            no_improvement = 0
            # print(f"{no_experimented + 1}: Improved! New cost is {new_cost}")
            # update best result, then next improvement will continue from the best
            best_tour = new_tour
            best_cost = new_cost
        else:
            no_improvement += 1
            # print(f"{no_experimented + 1}: {no_improvement} times has no improvement")
        no_experimented += 1

    return best_tour, best_cost


if __name__ == "__main__":
    # Distance matrix for 50 cities
    matrix = read_matrix("./data/TSP Matrix-1.txt")
    print(matrix)

    # init_path, init_distance = tsp_random(matrix)
    # print(f"Initial solution (random):")
    # print(f"- Path: {init_path}")
    # print(f"- Distance: {init_distance}")

    init_path, init_distance = tsp_greedy(matrix)
    print(f"Initial solution (greedy search):")
    print(f"- Path: {init_path}")
    print(f"- Distance: {init_distance}")

    times = 0
    max_algorithms = 10
    best_distance = -1
    best_paths = []
    while times < max_algorithms:
        path, distance = hill_climbing(matrix, init_path, 25)
        if best_distance == -1:
            best_distance = distance
            best_paths = path
        elif best_distance > distance:
            best_distance = distance
            best_paths = path
        print(f"No. {times + 1}: Cost= {distance}")
        print(f"- Path: {path}")
        times += 1
    print(f"best path: {best_distance}")
    print(f"best path: {best_paths}")
