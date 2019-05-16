#include <vector>
#include <iostream>
#include <string>
using namespace std;

/* Question 1 => sans template */
// inline void set_intersect(std::vector<int>::const_iterator a_begin,
//                         std::vector<int>::const_iterator a_end,
//                         std::vector<int>::const_iterator b_begin,
//                         std::vector<int>::const_iterator b_end,
//                         std::back_insert_iterator<std::vector<int>> c_begin){
//     for (auto ita = a_begin; ita != a_end; ita++){
//         for (auto itb = b_begin; itb != b_end; itb++){
//             if (*ita == *itb) {
//                 *(c_begin) = *ita;
//                 c_begin++;
//             }
//         }
//     }
// }

// inline void set_union(std::vector<int>::const_iterator a_begin,
//                     std::vector<int>::const_iterator a_end,
//                     std::vector<int>::const_iterator b_begin,
//                     std::vector<int>::const_iterator b_end,
//                     std::back_insert_iterator<std::vector<int>> c_begin){
//     for (auto ita = a_begin; ita != a_end; ita++) {
//         *(c_begin) = *ita;
//         c_begin++;
//     }
//     for (auto itb = b_begin; itb != b_end; itb++){
//         bool found = false;
//         for (auto ita = a_begin; ita != a_end; ita++){
//              if (*ita == *itb) {
//                 found = true;
//             }
//         }
//         if (!found) {
//             *(c_begin) = *itb;
//             c_begin++;
//         }
//     }
// }



/* Question 2 => avec template */
template <class T>
void set_intersect(typename T::const_iterator a_begin,
                        typename T::const_iterator a_end,
                        typename T::const_iterator b_begin,
                        typename T::const_iterator b_end,
                        typename std::back_insert_iterator<T> c_begin){
    for (auto ita = a_begin; ita != a_end; ita++){
        for (auto itb = b_begin; itb != b_end; itb++){
            if (*ita == *itb) {
                *(c_begin) = *ita;
                c_begin++;
            }
        }
    }
}

template <class T>
void set_union(typename T::const_iterator a_begin,
                    typename T::const_iterator a_end,
                    typename T::const_iterator b_begin,
                    typename T::const_iterator b_end,
                    typename std::back_insert_iterator<T> c_begin){
    for (auto ita = a_begin; ita != a_end; ita++){
        *(c_begin) = *ita;
        c_begin++;
    }
    for (auto itb = b_begin; itb != b_end; itb++){
        bool found = false;
        for (auto ita = a_begin; ita != a_end; ita++){
             if (*ita == *itb) {
                found = true;
            }
        }
        if (!found) {
            *(c_begin) = *itb;
            c_begin++;
        }
    }
}



// Question 6 : Generalisation avec template additionnel (comparaison)
template <class Const_iterator1, class Const_iterator2, class Back_insert_iterator, class F>
void set_intersect_comp(Const_iterator1 a_begin,
                        Const_iterator1 a_end,
                        Const_iterator2 b_begin,
                        Const_iterator2 b_end,
                        F fctComp,
                        Back_insert_iterator c_begin){
    for (auto ita = a_begin; ita != a_end; ita++){
        for (auto itb = b_begin; itb != b_end; itb++){
            if (fctComp(*ita, *itb)) {
                *(c_begin) = *ita;
                c_begin++;
            }
        }
    }
}
