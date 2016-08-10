//
//  SimpleHeap.swift
//  DoubleEndedRouteHeap
//
//  Created by Mathias Quintero on 8/10/16.
//  Copyright © 2016 Mathias Quintero. All rights reserved.
//

import Foundation
class SimpleHeap<T: Comparable> {
    
    var data: [T] {
        get {
            return heap.data.map() { $0.data }
        }
    }
    
    var size: Int {
        get {
            return heap.size
        }
    }
    
    var min: T? {
        get {
            return heap.min
        }
    }
    
    var max: T? {
        get {
            return heap.max
        }
    }
    
    let heap = Heap<T,T>()
    
    func insert(value: T) {
        heap.insert(value, data: value)
    }
    
    func popMin() -> T? {
        return heap.popMin()
    }
    
    func popRight() -> T? {
        return heap.popRight()
    }
    
}